/*
 * Copyright (c) 2011, Serge Rieder and others. All Rights Reserved.
 */

package org.jkiss.dbeaver.model;

import org.jkiss.utils.CommonUtils;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.ui.editors.sql.format.SQLFormatter;
import org.jkiss.dbeaver.ui.editors.sql.format.SQLFormatterConfiguration;
import org.jkiss.dbeaver.ui.editors.sql.syntax.SQLSyntaxManager;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL Utils
 */
public final class SQLUtils {

    //static final Log log = LogFactory.getLog(SQLUtils.class);

    public static final String TOKEN_TRANSFORM_START = "/*DB[*/";
    public static final String TOKEN_TRANSFORM_END = "/*]DB*/";

    public static final Pattern PATTERN_XFORM = Pattern.compile(Pattern.quote(TOKEN_TRANSFORM_START) + "[^" + Pattern.quote(TOKEN_TRANSFORM_END) + "]*" + Pattern.quote(TOKEN_TRANSFORM_END));

    public static final Pattern PATTERN_OUT_PARAM = Pattern.compile("((\\?)|(:[a-z0-9]+))\\s*:=");

    public static String stripTransformations(String query)
    {
        return PATTERN_XFORM.matcher(query).replaceAll("");
    }

    public static String stripComments(DBPDataSource dataSource, String query)
    {
        return stripComments(query, "/*", "*/", dataSource.getContainer().getKeywordManager().getSingleLineComments());
    }

    public static String stripComments(String query, String mlCommentStart, String mlCommentEnd, String[] slComments)
    {
        query = query.trim();
        Pattern stripPattern = Pattern.compile(
            "(\\s*" + Pattern.quote(mlCommentStart) +
            "[^" + Pattern.quote(mlCommentEnd) +
            "]*" + Pattern.quote(mlCommentEnd) +
            "\\s*)[^" + Pattern.quote(mlCommentStart) + "]*");
        Matcher matcher = stripPattern.matcher(query);
        if (matcher.matches()) {
            query = query.substring(matcher.end(1));
        }
        for (String slComment : slComments) {
            while (query.startsWith(slComment)) {
                int crPos = query.indexOf('\n');
                if (crPos == -1) {
                    break;
                } else {
                    query = query.substring(crPos).trim();
                }
            }
        }
        return query;
    }

    public static List<String> splitFilter(String filter)
    {
        if (CommonUtils.isEmpty(filter)) {
            return Collections.emptyList();
        }
        return CommonUtils.splitString(filter, ',');
    }

    public static boolean matchesAnyLike(String string, Collection<String> likes)
    {
        for (String like : likes) {
            if (matchesLike(string, like)) {
                return true;
            }
        }
        return false;
    }

    public static String makeLikePattern(String like)
    {
        return like.replace("%", ".*").replace("_", ".");
    }

    public static boolean matchesLike(String string, String like)
    {
        Pattern pattern = Pattern.compile(makeLikePattern(like), Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        return pattern.matcher(string).matches();
    }

    public static String escapeString(String string)
    {
        return string.replaceAll("'", "\\'");
    }

    public static String getFirstKeyword(String query)
    {
        int startPos = 0, endPos = -1;
        for (int i = 0; i < query.length(); i++) {
            if (Character.isLetterOrDigit(query.charAt(i))) {
                startPos = i;
                break;
            }
        }
        for (int i = startPos; i < query.length(); i++) {
            if (Character.isWhitespace(query.charAt(i))) {
                endPos = i;
                break;
            }
        }
        if (endPos == -1) {
            return query;
        }
        return query.substring(startPos, endPos);
    }

    public static String getQueryOutputParameter(DBCExecutionContext context, String query)
    {
        final Matcher matcher = PATTERN_OUT_PARAM.matcher(query);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * Removes \\r characters from query.
     * Actually this is done specially for Oracle due to some bug in it's driver
     * @param query query
     * @return normalized query
     */
    public static String makeUnifiedLineFeeds(String query)
    {
        if (query.indexOf('\r') == -1) {
            return query;
        }
        StringBuilder result = new StringBuilder(query.length());
        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i);
            if (c == '\r') {
                continue;
            }
            result.append(c);
        }
        return result.toString();
    }

    public static String formatSQL(DBPDataSource dataSource, String query)
    {
        SQLSyntaxManager syntaxManager = new SQLSyntaxManager();
        syntaxManager.setDataSource(dataSource);
        SQLFormatterConfiguration configuration = new SQLFormatterConfiguration(syntaxManager);
        configuration.setKeywordCase(SQLFormatterConfiguration.KEYWORD_UPPER_CASE);
        SQLFormatter formatter = new SQLFormatter(configuration);
        return formatter.format(query);
    }

}
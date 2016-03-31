package com.chatalytics.compute.db.dao;

import com.chatalytics.core.config.ChatAlyticsConfig;

/**
 * Factory for constructing DAOs.
 *
 * @author giannis
 *
 */
public class ChatAlyticsDAOFactory {

    private static IChatAlyticsDAO chatalyticsDao;
    private static IEntityDAO entityDao;

    private ChatAlyticsDAOFactory() {
        // hide constructor
    }

    public static IChatAlyticsDAO getChatAlyticsDao(ChatAlyticsConfig config) {
        if (chatalyticsDao == null) {
            chatalyticsDao = new ChatAlyticsDAOImpl(config);
        }
        return chatalyticsDao;
    }

    public static IEntityDAO getEntityDAO(ChatAlyticsConfig config) {
        if (entityDao == null) {
            entityDao = new EntityDAOImpl(config);
        }
        return entityDao;
    }
}

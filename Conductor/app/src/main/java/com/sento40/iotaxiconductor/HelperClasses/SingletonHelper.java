package com.sento40.iotaxiconductor.HelperClasses;

/**
 * @author RC - May 08, 2019
 * @version 0.1.0
 * @since 0.1.0
 *
 * This activity represents a form by notifications
 */
public class SingletonHelper {

    private static int currentFragment = 0;

    public static int getCurrentFragment() {
        return currentFragment;
    }

    public static void setAddCurrentFragment() {
        SingletonHelper.currentFragment = currentFragment + 1;
    }

    public static void setRemoveCurrentFragment() {
        SingletonHelper.currentFragment = currentFragment - 1;
    }

    public static void setRestoreCurrentFragment() {
        SingletonHelper.currentFragment = 0;
    }

}

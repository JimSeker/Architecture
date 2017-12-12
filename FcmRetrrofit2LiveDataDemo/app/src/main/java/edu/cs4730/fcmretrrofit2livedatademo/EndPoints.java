package edu.cs4730.fcmretrrofit2livedatademo;

/**
 * These are just constants, stored here, so if the backend server changes it's quick and easy to fix
 * them all here, instead of through out the code.
 *
 */

public class EndPoints {
    public static final String URL_BASE = "http://10.216.218.12/";
    public static final String URL_REGISTER_DEVICE = "http://10.216.218.12/rest/fcm/RegisterDevice.php";
    public static final String URL_SEND_SINGLE_PUSH = "http://10.216.218.12/FcmDemo/sendSinglePush.php";
    public static final String URL_SEND_MULTIPLE_PUSH = "http://10.216.218.12/FcmDemo/sendMultiplePush.php";
    public static final String URL_FETCH_DEVICES = "http://10.216.218.12/FcmDemo/GetRegisteredDevices.php";
}

package rebat.shoura;

import android.provider.BaseColumns;

/**
 * Created by wassanalluhaidan on 2/10/17.
 */

public class Entities {

    public class Consultant {
        String Username = "Consultant";
        String Name = "المستشار";
        String Field = "المجال";
        String Bio = "نبذة نبذة نبذة نبذة نبذة نبذة نبذة نبذة نبذة نبذة نبذة ";
        String Email = "email@domain.com";
        String Password = "email";
        byte[] Photo;
    }

    public static abstract class User implements BaseColumns
    {
        public static final String USER_NAME = "user_name";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_AGE ="user_age";
        public static final String USER_GENDER = "user_gender";
        public static final String USER_STATUS ="user_status";
        public static final String USER_JOB ="user_job";

        public static final String DATABASE_NAME = "user_profile";

        public static final String TABLE_NAME = "reg_info";
    }
}

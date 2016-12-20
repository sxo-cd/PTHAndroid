package me.passtheheadphones.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by stu on 18/12/16.
 */

public class Profile {

    @SerializedName("status")
    public String status;
    @SerializedName("response")
    public Response response;

    public static class Stats {
        @SerializedName("joinedDate")
        public Date joinedDate;
        @SerializedName("lastAccess")
        public Date lastAccess;
        @SerializedName("uploaded")
        public String uploaded;
        @SerializedName("downloaded")
        public String downloaded;
        @SerializedName("ratio")
        public double ratio;
        @SerializedName("requiredRatio")
        public double requiredRatio;
    }

    public static class Ranks {
        @SerializedName("uploaded")
        public int uploaded;
        @SerializedName("downloaded")
        public int downloaded;
        @SerializedName("uploads")
        public int uploads;
        @SerializedName("requests")
        public int requests;
        @SerializedName("bounty")
        public int bounty;
        @SerializedName("posts")
        public int posts;
        @SerializedName("artists")
        public int artists;
        @SerializedName("overall")
        public int overall;
    }

    public static class Personal {
        @SerializedName("class")
        public String mclass;
        @SerializedName("paranoia")
        public int paranoia;
        @SerializedName("paranoiaText")
        public String paranoiaText;
        @SerializedName("donor")
        public boolean donor;
        @SerializedName("warned")
        public boolean warned;
        @SerializedName("enabled")
        public boolean enabled;
        @SerializedName("passkey")
        public String passkey;
    }

    public static class Community {
        @SerializedName("posts")
        public int posts;
        @SerializedName("torrentComments")
        public int torrentComments;
        @SerializedName("collagesStarted")
        public int collagesStarted;
        @SerializedName("collagesContrib")
        public int collagesContrib;
        @SerializedName("requestsFilled")
        public int requestsFilled;
        @SerializedName("requestsVoted")
        public int requestsVoted;
        @SerializedName("perfectFlacs")
        public int perfectFlacs;
        @SerializedName("uploaded")
        public int uploaded;
        @SerializedName("groups")
        public int groups;
        @SerializedName("seeding")
        public int seeding;
        @SerializedName("leeching")
        public int leeching;
        @SerializedName("snatched")
        public int snatched;
        @SerializedName("invited")
        public int invited;
    }

    public static class Response {
        @SerializedName("username")
        public String username;
        @SerializedName("avatar")
        public String avatar;
        @SerializedName("isFriend")
        public boolean isFriend;
        @SerializedName("profileText")
        public String profileText;
        @SerializedName("stats")
        public Stats stats;
        @SerializedName("ranks")
        public Ranks ranks;
        @SerializedName("personal")
        public Personal personal;
        @SerializedName("community")
        public Community community;
    }
}

package za.ac.cput.factory;

import za.ac.cput.domain.CommunityPost;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class CommunityPostFactory {

    public static CommunityPost createCommunityPost(String postId,
                                                    String title,
                                                    String content,
                                                    User user) {

        if (Helper.isEmptyOrNull(postId)) {
            return null;
        }

        if (Helper.isEmptyOrNull(title)) {
            return null;
        }

        if (Helper.isEmptyOrNull(content)) {
            return null;
        }

        if (Helper.isValidType(user)) {
            return null;
        }

        CommunityPost.Builder builder = new CommunityPost.Builder();

        builder.setPostId(postId);
        builder.setTitle(title);
        builder.setContent(content);
        builder.setUser(user);

        return builder.build();
    }
}
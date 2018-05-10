package com.openup.data.pref;


/**
 * Created by Niel on 19/07/17.
 */

public interface IPreferenceHelper {

  int getCurrentUserLoggedInMode();

  Long getCurrentUserId();

  void setCurrentUserId(Long userId);

  String getCurrentUserName();

  void setCurrentUserName(String userName);

  String getCurrentUserEmail();

  void setCurrentUserEmail(String email);

  String getCurrentUserProfilePicUrl();

  void setCurrentUserProfilePicUrl(String profilePicUrl);

  String getAccessToken();

  void setAccessToken(String accessToken);
}

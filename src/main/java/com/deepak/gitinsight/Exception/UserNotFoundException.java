package com.deepak.gitinsight.Exception;

public class UserNotFoundException extends RuntimeException {
   public UserNotFoundException (String message) {
    super(message);
  }
}

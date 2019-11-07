/**
 * User
 */
public class User {

  private String username;
  private String password;
  
  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  public static Builder getBuilder() {
    return new Builder();
  }

  public static class Builder {
    
    private String username;
    private String password;

    /**
     * @param username the username to set
     */
    public Builder setUsername(String username) {
      this.username = username;
      return this;
    }

    /**
     * @param password the password to set
     */
    public Builder setPassword(String password) {
      this.password = password;
      return this;
    }

    public User build() {
      return new User(this);
    }

    @Override
    public String toString() {
      // TODO Auto-generated method stub
      return "Builder {username=" + username + ", password=" + password + "}"; 
    }

    private Builder() {}
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "User {username=" + username + ", password=" + password + "}"; 
  }

  private User(Builder builder) {
    this.username = builder.username;
    this.password = builder.password;
  }

  public static void main(String[] args) {
    User user = User.getBuilder().setUsername("hello").setPassword("123456").build();
    System.out.println(user);
  }
}
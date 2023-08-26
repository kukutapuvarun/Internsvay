import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class CrudApplication {
    private List<User> users;
    private int nextUserId;

    public CrudApplication() {
        users = new ArrayList<>();
        nextUserId = 1;
    }

    public void createUser(String name) {
        User newUser = new User(nextUserId++, name);
        users.add(newUser);
        System.out.println("User created: " + newUser.getName());
    }

    public User readUser(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(int userId, String newName) {
        User user = readUser(userId);
        if (user != null) {
            user.setName(newName);
            System.out.println("User updated: " + user.getName());
        } else {
            System.out.println("User not found");
        }
    }

    public void deleteUser(int userId) {
        User user = readUser(userId);
        if (user != null) {
            users.remove(user);
            System.out.println("User deleted: " + user.getName());
        } else {
            System.out.println("User not found");
        }
    }

    public void displayUsers() {
        System.out.println("User List:");
        for (User user : users) {
            System.out.println(user.getId() + ": " + user.getName());
        }
    }

    public static void main(String[] args) {
        CrudApplication app = new CrudApplication();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Display Users");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    app.createUser(userName);
                    break;
                case 2:
                    System.out.print("Enter user ID: ");
                    int readId = scanner.nextInt();
                    User readUser = app.readUser(readId);
                    if (readUser != null) {
                        System.out.println("User found: " + readUser.getName());
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.print("Enter new user name: ");
                    String updatedName = scanner.nextLine();
                    app.updateUser(updateId, updatedName);
                    break;
                case 4:
                    System.out.print("Enter user ID: ");
                    int deleteId = scanner.nextInt();
                    app.deleteUser(deleteId);
                    break;
                case 5:
                    app.displayUsers();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
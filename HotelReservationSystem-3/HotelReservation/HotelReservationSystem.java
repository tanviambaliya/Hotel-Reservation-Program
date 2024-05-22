import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Objects;

class Customer {
    private String name;
    private int age;
    private String phone;
    private String email;
    private String address;
    private String password;
    public Customer(String name, int age, String phone, String email, String address, String 
    password) {
    this.name = name;
    this.age = age;
    this.phone = phone;
    this.email = email;
    this.address = address;
    this.password = password;
    }
    // Getters and setters for all fields
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    @Override
    public String toString() {
    return "Customer{" +
    "Name='" + name + '\'' +
    ", Age=" + age +
    ", phone='" + phone + '\'' +
    ", email='" + email + '\'' +
    ", address='" + address + '\'' +
    '}';
    }
}
class Room {
    private int roomNumber;
    private String roomType;
    private int beds;
    private boolean isBooked;

    public Room(int roomNumber, String roomType, int beds) {
    this.roomNumber = roomNumber;
    this.roomType = roomType;
    this.beds = beds;
    this.isBooked = false;
    }
    // Getters and setters for all fields
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public int getBeds() { return beds; }
    public void setBeds(int beds) { this.beds = beds; }
    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { this.isBooked = booked; }

    @Override
    public String toString() {
    return "Room{" +
    "roomNumber=" + roomNumber +
    ", roomType='" + roomType + '\'' +
    ", beds=" + beds +
    ", isBooked=" + isBooked +
    '}';
    }
}
class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters for all fields...

    @Override
    public String toString() {
        return "Admin{" +
                "Username='" + username + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    // Equals and hashcode for username (for easier comparison)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(username, admin.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
class Hotel {
    private String name;
    private List<Room> rooms;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void deleteRoom(int roomNumber) {
        rooms.removeIf(room -> room.getRoomNumber() == roomNumber);
    }

    public void displayAllRooms() {
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}

public class HotelReservationSystem {

    private List<Customer> customers;
    private List<Room> rooms;
    private List<Admin> admins;
    private List<Hotel> hotels;

    public HotelReservationSystem() { 
    customers = new ArrayList<>();
    rooms = new ArrayList<>();
    admins = new ArrayList<>();
    hotels = new ArrayList<>();
   
    }

    public void registerCustomer(String name, int age, String phone, String email, String 
    address, String password) {
    Customer customer = new Customer(name, age, phone, email, address, password);
    customers.add(customer);
    }

    public Customer loginCustomer(String email, String password) {
    for (Customer customer : customers) {
        if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) 
        {
        return customer;
        }
    }
    return null;
    }

    public void registerAdmin(String username, String password) {
        Admin admin = new Admin(username, password);
        admins.add(admin);
    }

    public Admin loginAdmin(String username, String password) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    public void addHotel(String name) {
        Hotel hotel = new Hotel(name);
        hotels.add(hotel);
    }

    public void addRoomToHotel(int hotelIndex, Room room) {
        if (hotelIndex >= 0 && hotelIndex < hotels.size()) {
            hotels.get(hotelIndex).addRoom(room);
        } else {
            System.out.println("Invalid hotel index.");
        }
    }

    public void deleteRoomFromHotel(int hotelIndex, int roomNumber) {
        if (hotelIndex >= 0 && hotelIndex < hotels.size()) {
            hotels.get(hotelIndex).deleteRoom(roomNumber);
        } else {
            System.out.println("Invalid hotel index.");
        }
    }

    public void displayAllRoomsInHotel(int hotelIndex) {
        if (hotelIndex >= 0 && hotelIndex < hotels.size()) {
            hotels.get(hotelIndex).displayAllRooms();
        } else {
            System.out.println("Invalid hotel index.");
        }
    }

    public void displayAllAdmins() {
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }

    public boolean bookRoom(int roomNumber) {
    for (Room room : rooms) {
        if (room.getRoomNumber() == roomNumber && !room.isBooked()) {
        room.setBooked(true);
        return true;
        }
    }
    return false;
    }

    public boolean cancelBooking(int roomNumber) {
    for (Room room : rooms) {
        if (room.getRoomNumber() == roomNumber && room.isBooked()) {
        room.setBooked(false);
        return true;
        }
    }
    return false;
    }

    public void addRoom(int roomNumber, String roomType, int beds) {
    Room room = new Room(roomNumber, roomType, beds);
    rooms.add(room);
    }

    public void displayRooms() {
    for (Room room : rooms) {
        if (!room.isBooked()) {
        System.out.println(room);
        }
    }
    }

    public void updateCustomerData(String NewEmail, String newName, int newAge, String 
    newPhone, String newAddress) {
    for (Customer customer : customers) {
        if (customer.getEmail().equals(NewEmail)) {
        customer.setName(newName);
        customer.setAge(newAge);
        customer.setPhone(newPhone);
        customer.setAddress(newAddress);
        return;
        }
    }
    }

    public void deleteCustomer(String email) {
    customers.removeIf(customer -> customer.getEmail().equals(email));
    }

    public void displayAllCustomers() {
    for (Customer customer : customers) {
    System.out.println(customer);
    }
    }

    public void displayAllBookedRooms() {
    List<Room> bookedRooms = 
    rooms.stream().filter(Room::isBooked).collect(Collectors.toList());
    for (Room room : bookedRooms) {
    System.out.println(room);
    }
    }

 public static void main(String[] args) {
    HotelReservationSystem system = new HotelReservationSystem();
    Scanner scanner = new Scanner(System.in);
    Customer loggedInCustomer = null;
    Admin loggedInAdmin = null;

    // Initialize some rooms
    system.addRoom(101, "Single", 1);
    system.addRoom(102, "Double", 2);
    system.addRoom(103, "Deluxe", 3);
    system.addRoom(201, "Single", 4);
    system.addRoom(202, "Double", 5);
    system.addRoom(203, "Deluxe", 6);

    while (true) {
        if (loggedInCustomer == null && loggedInAdmin == null) {
        // Display only login and registration options
        System.out.println("\nWelcome to the Hotel Reservation System");
        System.out.println("1. Register as Customer");
        System.out.println("2. Customer Login");
        System.out.println("3. Admin Login");
        System.out.println("4. Register as Admin");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1: // Customer registration
            System.out.print("Enter FullName: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age = scanner.nextInt(); scanner.nextLine();
            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();
            System.out.print("Enter Email Address: ");
            String email = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            System.out.print("Set up a Password: ");
            String password = scanner.nextLine();
            system.registerCustomer(name, age, phone, email, address, password);
            System.out.println("Customer registered successfully.");
            break;
        
            case 2: // Customer login
            System.out.print("Enter Email Address: ");
            String loginEmail = scanner.nextLine();
            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();
            loggedInCustomer = system.loginCustomer(loginEmail, loginPassword);
            if (loggedInCustomer != null) {
            System.out.println("Customer Logged in successfully.");
            } else {
            System.out.println("Login failed. Please check your credentials.");
            }
            break;
        
            case 3: // Admin login
            System.out.println("Enter Admin Username:");
            String adminUsername = scanner.nextLine();
            System.out.println("Enter Admin Password:");
            String adminPassword = scanner.nextLine();
            loggedInAdmin = system.loginAdmin(adminUsername, adminPassword);
            if (loggedInAdmin != null) {
                System.out.println("Admin logged in successfully.");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
            break;
        
            case 4: // ADMIN REGISTRATION
            System.out.println("Enter Admin Username:");
            String newAdminUsername = scanner.nextLine();
            System.out.println("Enter Admin Password:");
            String newAdminPassword = scanner.nextLine();
            system.registerAdmin(newAdminUsername, newAdminPassword);
            System.out.println("Admin registered successfully.");
            break;
        
            case 5: // Exit
            System.out.println("Thank you for using the Hotel Reservation System.");
            scanner.close();
            return;
        
            default:
            System.out.println("Invalid option. Please try again.");
            }        
        } else if (loggedInAdmin != null) {
            // Admin menu
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Hotel");
            System.out.println("2. Add Room to Hotel");
            System.out.println("3. Delete Room from Hotel");
            System.out.println("4. Display All Rooms in Hotel");
            System.out.println("5. Display All Customers");
            System.out.println("6. Display All Admins");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (adminChoice) {
                case 1:
                    // Add Hotel
                    System.out.println("Enter Hotel Name:");
                    String hotelName = scanner.nextLine();
                    system.addHotel(hotelName);
                    System.out.println("Hotel added successfully.");
                    break;
                case 2:
                    // Add Room to Hotel
                    System.out.println("Enter Hotel Index:");
                    int hotelIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter Room Number:");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter Room Type:");
                    String roomType = scanner.nextLine();
                    System.out.println("Enter Number of Beds:");
                    int beds = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    system.addRoomToHotel(hotelIndex, new Room(roomNumber, roomType, beds));
                    System.out.println("Room added to hotel successfully.");
                    break;
                case 3:
                    // Delete Room from Hotel
                    System.out.println("Enter Hotel Index:");
                    int delHotelIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter Room Number to Delete:");
                    int delRoomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    system.deleteRoomFromHotel(delHotelIndex, delRoomNumber);
                    System.out.println("Room deleted from hotel successfully.");
                    break;
                case 4:
                    // Display All Rooms in Hotel
                    System.out.println("Enter Hotel Index:");
                    int displayHotelIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    system.displayAllRoomsInHotel(displayHotelIndex);
                    break;
                case 5:
                    // Display All Customers
                    system.displayAllCustomers();
                    break;
                case 6:
                    // Display All Admins
                    system.displayAllAdmins();
                    break;
                case 7:
                    // Logout
                    loggedInAdmin = null;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } else {
        // Display all options for logged-in users
        System.out.println("\nWelcome to the Hotel Reservation System");
        System.out.println("1. Book a Room");
        System.out.println("2. Cancel a Booking");
        System.out.println("3. Display Available Rooms");
        System.out.println("4. Update My Data");
        System.out.println("5. Delete My Account");
        System.out.println("6. Display All Customers");
        System.out.println("7. Display All Booked Rooms");
        System.out.println("8. Logout");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch(choice){

            case 1:
            // Book a room
            system.displayRooms();
            System.out.print("Enter room number to book: ");
            int roomNumberToBook = scanner.nextInt();
            if (system.bookRoom(roomNumberToBook)) {
            System.out.println("Room booked successfully.");
            } else {
            System.out.println("Room booking failed.");
            }
            break;
            case 2:
            // Cancel a booking
            System.out.print("Enter room number to cancel booking: ");
            int roomNumberToCancel = scanner.nextInt();
            if (system.cancelBooking(roomNumberToCancel)) {
            System.out.println("Booking cancelled successfully.");
            } else {
            System.out.println("Cancellation failed.");
            }
            break;
            case 3:
            system.displayRooms();
            break;
            
            case 4:
            // Update customer data
            System.out.print("Enter your Email Address: ");
            String newEmail = scanner.nextLine();
            System.out.print("Enter New Name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter New Age: ");
            int newAge = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Enter New PhoneNumber: ");
            String newPhone = scanner.nextLine();
            System.out.print("Enter New Address: ");
            String newAddress = scanner.nextLine();
            system.updateCustomerData(newEmail, newName, newAge, newPhone, 
            newAddress);
            System.out.println("Customer Data Updated.");
            break;
            case 5:
            // Delete customer account
            System.out.print("Enter your Email Address to delete account: ");
            String delEmail = scanner.nextLine();
            system.deleteCustomer(delEmail);
            System.out.println("Customer Account deleted.");
            break;
            case 6:
            // Display all customers
            system.displayAllCustomers();
            break;
            case 7:
            // Display all booked rooms
            system.displayAllBookedRooms();
            break;
            case 8:
            // Exit
            System.out.println("Thank you for using the Hotel Reservation System.");
            scanner.close();
            return;
                
            default:
            System.out.println("Invalid option. Please try again.");
            }

        }

    }
 }
}

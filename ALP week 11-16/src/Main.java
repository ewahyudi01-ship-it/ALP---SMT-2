//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.InputMismatchException;
import java.util.Scanner;
import  java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                ArrayList<User> user = new ArrayList<>();
                boolean isloged = true;

                while (isloged) {
                    System.out.println("\n=== SMART CANTEEN ===");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.println("0. Exit");
                    System.out.print("Choice: ");
                    try {
                        int n = sc.nextInt();
                        switch (n) {
                            case 1:
                                register(sc, user);
                                break;

                            case 2:
                                login(sc, user);
                                break;

                            case 0:
                                System.out.println("Goodbye!");
                                isloged = false;
                                break;
                            default:
                                System.out.println("Wrong input!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Input with number!");
                        sc.next(); // tanpa perintah ini maka infinite loop
                    }

                }
            }

            public static void register(Scanner sc, ArrayList<User> user) {
                sc.nextLine();

                System.out.print("\ninsert username: ");
                String n = sc.nextLine();
                System.out.print("insert password: ");
                String n2 = sc.nextLine();
                System.out.print("Confirm password: ");
                String n3 = sc.nextLine();

                if (!n.isEmpty() || !n2.isEmpty() || !n3.isEmpty()) {
                    System.out.println("\nRoles: ");
                    System.out.println("1. siswa");
                    System.out.println("2. maha siswa");

                    int n4 = 0;
                    boolean n4Filled = true;
                    while (n4Filled) {
                        System.out.print("choose: ");
                        try {
                            n4 = sc.nextInt();
                            if (n4 == 1 || n4 == 2) {
                                n4Filled = false;
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Input with number!");
                            sc.next();
                        }
                    }

                    if (n4Filled == false) {
                        if (n2.equals(n3)) {
                            if (n4 == 1) {
                                User newUser = new Siswa(n, n3);
                                user.add(newUser);

                            } else if (n4 == 2) {
                                User newUser = new MahaSiswa(n, n3);
                                user.add(newUser);
                            }
                            System.out.println("=== Created new account! ===");

                        } else {
                            System.out.println("=== match the password! ===");
                        }
                    }
                }
            }

            public static void login(Scanner sc, ArrayList<User> user) {
                System.out.println("\nEnter username: ");
                String n = sc.nextLine();
                System.out.println("Enter password: ");
                String n2 = sc.nextLine();

                if (!n.isEmpty()  || !n2.isEmpty()) {
                    for (int i = 0; i < user.size(); i++) {
                        if(user.get(i).getNama().equals(n) && user.get(i).getPassword().equals(n2)) {

                            user.get(i).menuUtama();
                            System.out.println("=== Logged in successfully! ===");

                        }
                    }
                }
            }

        }


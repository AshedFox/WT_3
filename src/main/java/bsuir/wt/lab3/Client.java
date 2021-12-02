package bsuir.wt.lab3;

import bsuir.wt.lab3.entity.ArchiveFile;
import bsuir.wt.lab3.entity.MessageType;
import bsuir.wt.lab3.entity.Student;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public void startConnection(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ignored) {}
    }

    public String sendMessage(String message) {
        try {
            out.write(message);
            out.flush();

            return in.readLine();
        }
        catch (IOException e) {
            return null;
        }
    }

    public void stopConnection() {
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        var client = new Client();
        client.startConnection("127.0.0.1", 8080);

        String input;
        while (true) {
            System.out.print("""
                    Choose action:
                    1 - Authorize
                    2 - Get Archive File
                    3 - Get List Of Archive Files
                    4 - Create New Archive File
                    5 - Edit Existing Archive File
                    6 - Delete Existing Archive File
                    e - Exit
                    """);

            input = scanner.nextLine();

            switch (input){
                case "1" -> {
                    System.out.println("Enter email: ");
                    var email = scanner.nextLine();
                    System.out.println("Enter password: ");
                    var password = scanner.nextLine();

                    System.out.println(client.sendMessage("%s %s %s\n".formatted(MessageType.AUTH, email, password)));
                }
                case "2" -> {
                    System.out.println("Enter archive file id: ");
                    var name = scanner.nextLine();
                    System.out.println(client.sendMessage("%s %s\n".formatted(MessageType.GET_ARCHIVE_FILE, name)));
                }
                case "3" -> System.out.println(client.sendMessage("%s\n".formatted(MessageType.GET_ARCHIVE_FILES)));
                case "4" -> {
                    System.out.println("Enter student name: ");
                    var name = scanner.nextLine();
                    System.out.println("Enter student surname: ");
                    var surname = scanner.nextLine();
                    System.out.println("Enter student age: ");
                    var age = scanner.nextLine();

                    var student = new Student();
                    student.setName(name);
                    student.setSurname(surname);
                    student.setAge(Integer.parseInt(age));

                    System.out.println(client.sendMessage("%s %s\n".formatted(MessageType.POST_ARCHIVE_FILE, student)));
                }
                case "5" -> {
                    System.out.println("Enter archive file id: ");
                    var id = scanner.nextLine();
                    System.out.println("Enter student name: ");
                    var name = scanner.nextLine();
                    System.out.println("Enter student surname: ");
                    var surname = scanner.nextLine();;
                    System.out.println("Enter student age: ");
                    var age = scanner.nextLine();

                    var student = new Student();
                    student.setName(name);
                    student.setSurname(surname);
                    student.setAge(Integer.parseInt(age));

                    var archiveFile = new ArchiveFile();
                    archiveFile.setId(Integer.parseInt(id));
                    archiveFile.setStudent(student);

                    System.out.println(client.sendMessage("%s %s\n".formatted(MessageType.UPDATE_ARCHIVE_FILE, archiveFile)));
                }
                case "6" -> {
                    System.out.println("Enter archive id: ");
                    var code = scanner.nextLine();

                    System.out.println(client.sendMessage("%s %s\n".formatted(MessageType.DELETE_ARCHIVE_FILE, code)));
                }
                case "e" -> {
                    client.stopConnection();
                    System.exit(0);
                }
                default -> System.out.println("Incorrect action");
            }
        }
    }
}

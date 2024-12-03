package com.example.pcbuilder.runner;

//@Component
//public class DbInitRunner implements CommandLineRunner {
//
//    private static final Scanner scanner = new Scanner(System.in);
//
//    private final List<DbRandomWriter> randomWriters;
//
//    @Autowired
//    public DbInitRunner(List<DbRandomWriter> randomWriters) {
//        this.randomWriters = randomWriters;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Log.d("DataBase runner started");
//
//        System.out.println("Choose write mode: " +
//                "\n 1 - Auto fill" +
//                "\n 2 - Manually fill");
//
//        var input = scanner.nextInt();
//
//        switch (input) {
//            case 1 -> randomWriters.forEach((d) -> d.write(100));
//            case 2 -> System.out.println("Sorry this not be finished");
//            default -> System.out.println("I don`t know this command");
//        }
//
//        System.out.println("Done");
//    }
//}

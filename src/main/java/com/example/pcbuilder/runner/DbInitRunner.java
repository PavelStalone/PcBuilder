package com.example.pcbuilder.runner;

//@Component
//public class DbInitRunner implements CommandLineRunner {
//
//    private static final Scanner scanner = new Scanner(System.in);
//
//    private final List<DbRandomWriter> randomWriters;
//
//    @Autowired
//    private ClassFiller<CpuDto> cpuFiller;
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
////        switch (input) {
////            case 1 -> randomWriters.forEach((d) -> d.write(100));
////            case 2 -> System.out.println("Sorry this not be finished");
////            default -> System.out.println("I don`t know this command");
////        }
//
//        var cpu = cpuFiller.getFill();
//        System.out.println(cpu);
//        System.out.println(Mapper.createTypeMap(CpuDto.class, CpuViewModel.class).map(cpu));
//
//        System.out.println("Done");
//    }
//}

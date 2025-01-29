package BACKAPp.model;

// LA CLASSE è GIà APPOSTO COSì
public record Config(
    String sourcePath, 
    String destinationPath, 
    String date
) {
    public Config {
        if (sourcePath == null || destinationPath == null || date == null) {
            throw new IllegalArgumentException("Source path, destination path and date must not be null");
        }
        if(date.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }
}

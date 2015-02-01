/* Author: Luigi Vincent
A simple I/O workout app
*/

import javax.swing.SwingUtilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WorkoutApp implements WorkoutUiHandler {
    private static final Path file = Paths.get("Workout Log.txt");
    private final DateTimeFormatter dateTimeFormatter = 
    	DateTimeFormatter.ofPattern("MM/dd/yy HH:mm a")
    ;

    private final LocalDateTime start;
    private WorkoutPresenter workoutPresenter;

    public WorkoutApp() {
        start = LocalDateTime.now();
    }

    public static void main(String[] args) throws Exception {
        if (!Files.isWritable(file)) {
            throw new IOException("Output path is not writeable.");
        }
        SwingUtilities.invokeLater(() -> {
            final WorkoutApp application = new WorkoutApp();
            application.init();
        });
    }

    public void init() {
        workoutPresenter = new WorkoutPresenterImpl(this);

        final List<Workout> workouts = new ArrayList<>();
        final Workout pushUps = new Workout();
        pushUps.setName("Push ups");
        workouts.add(pushUps);
        final Workout dumbellPresses = new Workout();
        dumbellPresses.setName("Dumbell Presses");
        workouts.add(dumbellPresses);
        final Workout dumbellCurls = new Workout();
        dumbellCurls.setName("Dumbell Curls");
        workouts.add(dumbellCurls);

        workoutPresenter.edit(workouts);
    }

    @Override
    public void log() {
        final List<Workout> workouts = workoutPresenter.flush();
        try (final PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(file, StandardOpenOption.APPEND))) {
            printWriter.println("\n" + start.format(dateTimeFormatter));
            workouts.stream()
                    .filter(w -> w.getReps() > 0 && w.getSets() > 0)
                    .map(Workout::toFileRepresentation)
                    .forEach(printWriter::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

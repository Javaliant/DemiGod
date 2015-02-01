import java.util.List;

public interface WorkoutPresenter {

    void edit(List<Workout> workouts);

    List<Workout> flush();
}

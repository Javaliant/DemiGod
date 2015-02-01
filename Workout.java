public class Workout {
    private String name;
    private int reps, sets;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }
    public void setSets(int sets) {
        this.sets = sets;
    }

    public String toFileRepresentation() {
        return String.format("%s x %s %s", sets, reps, name);
    }
}

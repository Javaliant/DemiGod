import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import java.awt.BorderLayout;
import java.util.List;


public class WorkoutPresenterImpl extends JFrame implements WorkoutPresenter {

    private final WorkoutUiHandler workoutUiHandler;
    private final JPanel workoutPanel = new JPanel();
    private WorkoutTableModel workoutTableModel;

    public WorkoutPresenterImpl(WorkoutUiHandler workoutUiHandler) {
        this.workoutUiHandler = workoutUiHandler;
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("My Workout Tracker");
        add(workoutPanel, BorderLayout.CENTER);
        final JButton log = new JButton("Log");
        log.addActionListener(e -> workoutUiHandler.log());
        add(log, BorderLayout.SOUTH);
        setVisible(true);
        pack();
    }

    @Override
    public void edit(List<Workout> workouts) {
        workoutPanel.removeAll();
        workoutTableModel = new WorkoutTableModel(workouts);
        final JTable jTable = new JTable(workoutTableModel);
        final JScrollPane scrollPane = new JScrollPane(jTable);
        workoutPanel.add(scrollPane);
        pack();
    }

    @Override
    public List<Workout> flush() {
        return workoutTableModel.getWorkouts();
    }

    private static class WorkoutTableModel extends AbstractTableModel {

        private static final int WORKOUT_COL = 0;
        private static final int SET_COL = 1;
        private static final int REP_COL = 2;
        private static final String[] COLUMNS = {"Workout", "Sets", "Reps"};
        private final List<Workout> workouts;

        private WorkoutTableModel(List<Workout> workouts) {
            this.workouts = workouts;
        }

        public List<Workout> getWorkouts() {
            return workouts;
        }

        @Override
        public int getRowCount() {
            return workouts.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            final Workout workout = workouts.get(rowIndex);
            switch (columnIndex) {
                case WORKOUT_COL:
                    return workout.getName();
                case SET_COL:
                    return workout.getSets();
                case REP_COL:
                    return workout.getReps();
                default:
                    throw new IllegalArgumentException("Unknown column " + columnIndex);
            }
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == SET_COL || columnIndex == REP_COL) {
                return Integer.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == SET_COL || columnIndex == REP_COL;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            final Workout workout = workouts.get(rowIndex);
            switch (columnIndex) {
                case SET_COL:
                    workout.setSets((Integer) aValue);
                    break;
                case REP_COL:
                    workout.setReps((Integer) aValue);
                    break;
                default:
                    throw new IllegalArgumentException("Cannot edit " + columnIndex);
            }
        }
    }
}

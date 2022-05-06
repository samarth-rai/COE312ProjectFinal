public interface State {
    public void prev(Context context);
    public void next(Context context);
    public void printStatus(Context context);
    }
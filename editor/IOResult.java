package editor;

public class IOResult<T> {
    private T data;
    private boolean okay;

    public IOResult(boolean okay, T data) {
        this.okay = okay;
        this.data = data;
    }

    public boolean isOkay() {
        return okay;
    }

    public boolean hasData() {
        return data != null;
    }

    public T getData() {
        return data;
    }
}

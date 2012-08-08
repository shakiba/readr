package x.readr.req;

public abstract class RequestParam<T extends AbstractRequest<?, ?>> extends Params {

    protected final T wrapper;

    public RequestParam(T wrapper) {
        this.wrapper = wrapper;
        this.wrapper.add(this);
    }
}
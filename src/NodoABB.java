
public class NodoABB<T> {

    private NodoABB<T> SubIzq;
    public T Info;
    private NodoABB<T> SubDer;

    public NodoABB(T D) {
        Info = D;
        SubIzq = null;
        SubDer = null;
    }

    public NodoABB<T> getSubIzq() {
        return SubIzq;
    }

    public NodoABB<T> getSubDer() {
        return SubDer;
    }

    public void setSubIzq(NodoABB<T> Ap) {
        SubIzq = Ap;
    }

    public void setSubDer(NodoABB<T> Ap) {
        SubDer = Ap;
    }

}

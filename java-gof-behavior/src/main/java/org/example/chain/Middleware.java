package org.example.chain;

public abstract class Middleware {
    private Middleware next;

    /**
     * Builds chains of middleware objects.
     * Returns the next middleware so we can chain them like:
     * first.linkWith(second).linkWith(third);
     */
    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    /**
     * Subclasses will implement this method with concrete checks.
     */
    public abstract boolean check(HttpRequest request);

    /**
     * check the next object in chain or end
     */
    protected boolean checkNext(HttpRequest request) {
        if (next == null) {
            // Reached the end of the chain without failing
            return true;
        }
        return next.check(request);
    }
}
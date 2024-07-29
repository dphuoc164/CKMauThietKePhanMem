package observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Subcriber> subcribers = new ArrayList<>();

    public void subcribe(Subcriber subcriber) {
        subcribers.add(subcriber);
    }

    public void unSubcriber(Subcriber subcriber) {
        subcribers.remove(subcriber);
    }

    public void notifyChange() {
        for (Subcriber subcriber : subcribers) {
            subcriber.update();
        }
    }

}

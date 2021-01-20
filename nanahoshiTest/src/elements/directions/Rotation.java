package elements.directions;

public enum Rotation {

    LEFT(-2), RIGHT(2);

    Rotation(int idxChange){
        this.idxChange = idxChange;
    }

    int idxChange;

}


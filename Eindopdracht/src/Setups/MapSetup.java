package Setups;

import LinePrep.Line;

import java.util.ArrayList;
import java.util.List;

abstract public class MapSetup {
    protected List<Line> lines;
    protected List<Line> mapLines;
    public MapSetup() {
        this.lines = new ArrayList<>();
        this.mapLines = new ArrayList<>();
        initialiseLevel();
    }
    abstract public void initialiseLevel();
    public List<Line> getLines (){
        return this.lines;
    }
    public List<Line> getMapLines() {
        return this.mapLines;
    }

}

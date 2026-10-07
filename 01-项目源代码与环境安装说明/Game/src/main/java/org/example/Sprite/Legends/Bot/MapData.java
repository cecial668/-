package org.example.Sprite.Legends.Bot;

import java.util.List;

public class MapData {
    private List<Waypoint> waypoints;
    private List<EdgeData> edges;

    // getters and setters
    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public List<EdgeData> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeData> edges) {
        this.edges = edges;
    }
}
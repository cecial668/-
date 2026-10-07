package org.example.Sprite.Legends.Bot;

import java.util.*;
import org.example.Scenes.GameScene;
public class Dijkstra {

    private List<Waypoint> waypoints;
    private Map<Waypoint, List<Edge>> adjacencyList;
    private GameScene gameScene;

    public Dijkstra(List<Waypoint> waypoints, GameScene gameScene) {
        this.waypoints = waypoints;
        this.adjacencyList = new HashMap<>();
        this.gameScene = gameScene;

        buildGraph();
    }

    // 构建图的邻接表
    private void buildGraph() {
        if (gameScene.Mapkind == 1) {
            for (Waypoint waypoint : waypoints) {
                adjacencyList.put(waypoint, new ArrayList<>());
            }
    
            addEdge(waypoints.get(0), waypoints.get(1));
            addEdge(waypoints.get(1), waypoints.get(2));
            addEdge(waypoints.get(8), waypoints.get(2));
            addEdge(waypoints.get(3), waypoints.get(8));
            addEdge(waypoints.get(8), waypoints.get(9));
            addEdge(waypoints.get(1), waypoints.get(6));
            addEdge(waypoints.get(3), waypoints.get(9));
            addEdge(waypoints.get(3), waypoints.get(4));
            addEdge(waypoints.get(4), waypoints.get(7));
            addEdge(waypoints.get(4), waypoints.get(5));
            addEdge(waypoints.get(0), waypoints.get(6));
            addEdge(waypoints.get(6), waypoints.get(7));
            addEdge(waypoints.get(7), waypoints.get(5));
            addEdge(waypoints.get(10), waypoints.get(11));
            addEdge(waypoints.get(11), waypoints.get(4));
        }  else {
            for (Waypoint waypoint : waypoints) {
                adjacencyList.put(waypoint, new ArrayList<>());
            }
            addEdge(waypoints.get(0), waypoints.get(3));
            addEdge(waypoints.get(10), waypoints.get(13));
            addEdge(waypoints.get(1), waypoints.get(9));
            addEdge(waypoints.get(1), waypoints.get(5));
            addEdge(waypoints.get(5), waypoints.get(12));
            addEdge(waypoints.get(6), waypoints.get(12));
            addEdge(waypoints.get(0), waypoints.get(6));
            addEdge(waypoints.get(5), waypoints.get(7));
            addEdge(waypoints.get(7), waypoints.get(8));
            addEdge(waypoints.get(8), waypoints.get(4));
            addEdge(waypoints.get(4), waypoints.get(15));
            addEdge(waypoints.get(3), waypoints.get(15));
            addEdge(waypoints.get(4), waypoints.get(9));
            addEdge(waypoints.get(2), waypoints.get(3));
            addEdge(waypoints.get(2), waypoints.get(17));
            addEdge(waypoints.get(11), waypoints.get(17));
            addEdge(waypoints.get(11), waypoints.get(16));
            addEdge(waypoints.get(9), waypoints.get(16));
            addEdge(waypoints.get(10), waypoints.get(14));
            addEdge(waypoints.get(1), waypoints.get(14));
        }

    }
        

    /* 
    private void loadGraphFromJson(String filePath) {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(filePath);
            Type mapType = new TypeToken<Map<String, List<EdgeJson>>>() {}.getType();
            Map<String, List<EdgeJson>> map = gson.fromJson(reader, mapType);
            List<EdgeJson> edges = map.get("edges");

            // 构建图的邻接表
            for (Waypoint waypoint : waypoints) {
                adjacencyList.put(waypoint, new ArrayList<>());
            }

            // 处理每条边
            for (EdgeJson edgeJson : edges) {
                Waypoint from = waypoints.get(edgeJson.fromIndex);
                Waypoint to = waypoints.get(edgeJson.toIndex);
                addEdge(from, to);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
        */

    private void addEdge(Waypoint from, Waypoint to) {
        double distance = calculateDistance(from, to);
        adjacencyList.get(from).add(new Edge(to, distance));
        adjacencyList.get(to).add(new Edge(from, distance)); // 如果是双向图
    }

    private double calculateDistance(Waypoint a, Waypoint b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    public Waypoint findClosestWaypoint(double playerX, double playerY) {
        Waypoint closestWaypoint = null;
        double minDistance = Double.MAX_VALUE;

        for (Waypoint waypoint : waypoints) {
            double distance = calculateDistance(new Waypoint(playerX, playerY), waypoint);
            if (distance < minDistance) {
                minDistance = distance;
                closestWaypoint = waypoint;
            }
        }

        return closestWaypoint;
    }

    // 根据Dijkstra算法查找从Bot位置到目标位置的路径
    public List<Waypoint> findPath(Waypoint start, Waypoint end) {
        if (!adjacencyList.containsKey(start)) {
            adjacencyList.put(start, new ArrayList<>());
        }
        if (!adjacencyList.containsKey(end)) {
            adjacencyList.put(end, new ArrayList<>());
        }
        Map<Waypoint, Double> distances = new HashMap<>();
        Map<Waypoint, Waypoint> previous = new HashMap<>();
        PriorityQueue<Waypoint> queue = new PriorityQueue<>(Comparator.comparing(distances::get));

        for (Waypoint waypoint : waypoints) {
            distances.put(waypoint, Double.MAX_VALUE);
            previous.put(waypoint, null);
        }
        distances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Waypoint current = queue.poll();

            if (current.equals(end)) {
                break;
            }

            List<Edge> edges = adjacencyList.get(current);
            if (edges == null) {
                System.err.println("No adjacent nodes found for: " + current);
                continue; // 跳过这个节点，继续下一个
            }

            for (Edge edge : edges) {
                Waypoint neighbor = edge.to;
                double newDist = distances.get(current) + edge.weight;

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        List<Waypoint> path = new ArrayList<>();
        for (Waypoint at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // 检查是否存在到目标比Bot当前位置更近的路径点
    public boolean isCloserPathAvailable(Waypoint start, Waypoint target, double botX, double botY) {
        List<Waypoint> path = findPath(start, target);
    
        System.out.println("Path size: " + path.size());
        if (path.size() < 2) return false;
    
        Waypoint secondWaypoint = path.get(1);
    
        double distanceToPlayer = calculateDistance(new Waypoint(botX, botY), target);
        double distanceToSecondWaypoint = calculateDistance(new Waypoint(botX, botY), secondWaypoint);
    
        return distanceToSecondWaypoint < distanceToPlayer;
    }


    private static class Edge {
        Waypoint to;
        double weight;

        Edge(Waypoint to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
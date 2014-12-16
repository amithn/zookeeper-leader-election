package com.voicestreams.leader.election;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World");
        String zookeeperConnectionString = "localhost:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);

        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            public void takeLeadership(CuratorFramework client) throws Exception {
                // this callback will get called when you are the leader
                // do whatever leader work you need to and only exit
                // this method when you want to relinquish leadership
                while(true) {
                    System.out.println("I'm the leader now ");
                    Thread.sleep(1000);
                }
            }
        };

        client.start();

        LeaderSelector selector = new LeaderSelector(client, "/linux", listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();

        while (true) {
            System.out.println(" -- live --");
            Thread.sleep(10000);
        }
    }
}

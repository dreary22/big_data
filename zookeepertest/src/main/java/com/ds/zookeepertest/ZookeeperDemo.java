package com.ds.zookeepertest;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperDemo {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 */
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		
		Watcher wh = new Watcher(){

			public void process(WatchedEvent arg0) {

				System.out.println(arg0.getState());
				System.out.println("watch:"+arg0.toString());
				
			}
			
		};

		ZooKeeper zk = new ZooKeeper("master.tongtech:2181",30000,wh);
		
		
		//zk.create("/zoo3test/aa","myData2aa".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		String csn = Charset.defaultCharset().name();
		System.out.println(csn);
		
		System.out.println("/zoo3test:"+new String(zk.getData("/zoo3test",false,null)));
		System.out.println("/zoo3test/aa:"+new String(zk.getData("/zoo3test/aa",false,null)));
		
		//zk.close();
		while(1==1){
			;
		}
		
	}

}

package inClassProblem3_part2;

public class TestChannel {

	public static void main(String[] args){
			

        Thread node1, node2;
        BoundedBuffer buffer1;
        BoundedBuffer buffer2;
//        Queue unboundedBuffer;
//        ChannelQueue chQueue;
//        ChannelArray chArray;

        buffer1 = new BoundedBuffer(5, "channel 1"); 
        buffer2 = new BoundedBuffer(5, "channel 2");
//        unboundedBuffer = new Queue();
//        chQueue = new ChannelQueue(unboundedBuffer);
//        chArray = new ChannelArray(buffer);
        BidirectionalChannel twoWayChannel = new BidirectionalChannel(buffer1, buffer2);

        node1 = new Thread(new Node(twoWayChannel),"NODE 1");
        node2 = new Thread(new Node(twoWayChannel),"NODE 2");
        node1.start();
        node2.start();

	}

}

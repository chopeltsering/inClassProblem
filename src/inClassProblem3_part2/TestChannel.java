package inClassProblem3_part2;

public class TestChannel {

	public static void main(String[] args){
			

        Thread producer, consumer;
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

        producer = new Thread(new Producer(twoWayChannel),"NODE 1");
        consumer = new Thread(new Consumer(twoWayChannel),"NODE 2");
        producer.start();
        consumer.start();

	}

}

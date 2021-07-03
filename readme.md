I used here classes to represent a node and other class to represent the actual Doubly Linked List Data Structre.


as we have a doubly linked list
for each node must be able to look in both directions (forward and backward)
but why that neccessary , assume you in any moment wanna delete a specific node from the middle.
hence you need to glue the remaining two parts.
by taking the refrence address of the next node 
assiging it to next node in the previous node (before the deleted one)

sorting a doubly linked list is a little bit costing time but okay.
how we sorted it based on lexographical order.
it's easy to say if we iteratred to the whole double linked list and extracted the minimum node (which has smallest lexographical name , then phone number).

afterward delete that node by the above approach.

taking the deleted node (which is the minimal in that dll)
inserting it to a new double linked list called sorted.


class Item {
  final String image;
  final String name;
  final String time;

  Item({required this.image, required this.name, required this.time});
}

class ItemData {
  static List<Item> items = [
    Item(image: 'assets/images/img1.jpg', name: 'John', time: '10:00'),
    Item(image: 'assets/images/img2.jpg', name: 'Alice', time: '11:39'),
    Item(image: 'assets/images/img3.jpg', name: 'Bob', time: '1:00'),
    Item(image: 'assets/images/img4.jpg', name: 'Emily', time: '2:45'),
    Item(image: 'assets/images/img5.jpg', name: 'David', time: '4:20'),
    Item(image: 'assets/images/img6.jpg', name: 'Sarah', time: '6:15'),
    Item(image: 'assets/images/img3.jpg', name: 'Bob', time: '6:15'),
    Item(image: 'assets/images/img2.jpg', name: 'Alice', time: '6:15'),
    Item(image: 'assets/images/img1.jpg', name: 'John', time: '6:15'),
  ];
}

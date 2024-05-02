import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class Item {
  final String name;
  final String email;
  final String time;
  final String image;

  Item(
      {required this.name,
      required this.email,
      required this.time,
      required this.image});
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final List<Item> items = [
    Item(
      name: 'Himanshu',
      email: 'himanshu@gmail.com',
      time: "5:40",
      image: 'assets/images/1.jpg',
    ),
    Item(
      name: 'Sanjay',
      email: 'sanjay@gmail.com',
      time: "3:40",
      image: 'assets/images/2.jpg',
    ),
    Item(
      name: 'Divyesh',
      email: 'divyesh@gmail.com',
      time: "1:11",
      image: 'assets/images/3.jpg',
    ),
    Item(
      name: 'Dhvanik',
      email: 'dhvanik@gmail.com',
      time: "9:00",
      image: 'assets/images/1.jpg',
    ),
    Item(
      name: 'Yagnik',
      email: 'yagnik@gmail.com',
      time: "12:34",
      image: 'assets/images/2.jpg',
    )
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Center(child: Text(widget.title)),
      ),
      body: ListView.builder(
          itemCount: items.length,
          itemBuilder: (context, index) {
            return ListTile(
              leading: CircleAvatar(
                backgroundImage: AssetImage(items[index]
                    .image), // Use AssetImage instead of NetworkImage
              ),
              title: Row(
                  children: [Text(items[index].name), Text(items[index].time)]),
              subtitle: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [Text(items[index].email)],
              ),
              trailing: const Icon(
                Icons.navigate_next,
                color: Colors.black,
                size: 20,
              ),
            );
          }),
    );
  }
}

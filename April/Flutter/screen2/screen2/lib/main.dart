import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

void main() {
  runApp(const MyApp());
}

class Item {
  final String color;
  final String image;

  Item({required this.color, required this.image});
}

Color getColorFromString(String colorString) {
  switch (colorString.toLowerCase()) {
    case "red":
      return Colors.red;
    case "blue":
      return Colors.blue;
    case "yellow":
      return Colors.yellow;
    case "purple":
      return Colors.purple;
    default:
      return Colors.red; // Default color
  }
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // TRY THIS: Try running your application with "flutter run". You'll see
        // the application has a purple toolbar. Then, without quitting the app,
        // try changing the seedColor in the colorScheme below to Colors.green
        // and then invoke "hot reload" (save your changes or press the "hot
        // reload" button in a Flutter-supported IDE, or press "r" if you used
        // the command line to start the app).
        //
        // Notice that the counter didn't reset back to zero; the application
        // state is not lost during the reload. To reset the state, use hot
        // restart instead.
        //
        // This works for code too, not just values: Most code changes can be
        // tested with just a hot reload.
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final List<Item> items = [
    Item(
      color: "red",
      image: "lib/assets/images/1.jpg",
    ),
    Item(
      color: "blue",
      image: 'lib/assets/images/1.jpg',
    ),
    Item(
      color: "yellow",
      image: "lib/assets/images/1.jpg",
    ),
    Item(
      color: "purple",
      image: "lib/assets/images/1.jpg",
    ),
  ];

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
        appBar: AppBar(
          // TRY THIS: Try changing the color here to a specific color (to
          // Colors.amber, perhaps?) and trigger a hot reload to see the AppBar
          // change color while the other colors stay the same.
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          // Here we take the value from the MyHomePage object that was created by
          // the App.build method, and use it to set our appbar title.
          title: Text(widget.title),
        ),
        body: ListView(
          children: [
            for (var item in items)
              Container(
                height: 120,
                child: Row(
                  children: [
                    Container(
                      width: 7,
                      height: double.infinity,
                      color: getColorFromString(item.color),
                    ),
                    const Expanded(
                      child: Column(
                        children: [
                          Text(
                            "Hey flaterrers what i did with flutter",
                            style: TextStyle(
                                fontSize: 20, fontWeight: FontWeight.bold),
                          ),
                          Text("This is decoration in flutter UI"),
                        ],
                      ),
                    ),
                    Container(
                      height: double.infinity,
                      width: 120,
                      // color: Colors.red,
                      child: Stack(
                        alignment: Alignment.centerRight,
                        children: [
                          Container(
                            color: getColorFromString(item.color),
                            width: 70,
                            height: 70,
                          ),
                          Positioned(
                            right: 10,
                            bottom: 10,
                            child: Container(
                              foregroundDecoration: BoxDecoration(
                                boxShadow: [
                                  BoxShadow(
                                    color: Colors.black.withOpacity(
                                        0.5), // You can set the color of the shadow
                                    spreadRadius: 2, // Spread radius
                                    blurRadius: 5, // Blur radius
                                    offset: Offset(
                                        0, 3), // Changes position of shadow
                                  ),
                                ],
                                border: Border.all(
                                    color: Colors.white,
                                    width: 5,
                                    style: BorderStyle.solid),
                              ),
                              color: Colors.white,
                              height: 70,
                              width: 70,
                            ),
                          ),
                          Positioned(
                            right: 15,
                            bottom: 12,
                            child: Image(
                              image: AssetImage("lib/assets/images/1.jpg"),
                              width: 60,
                              height: 67,
                            ),
                          ),
                        ],
                      ),
                    )
                  ],
                ),
              ),
          ],
        ));
  }
}

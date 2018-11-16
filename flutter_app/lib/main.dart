import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final wordpair = WordPair.random();
    return MaterialApp(
      title: 'ClickyClicker',
      home: Scaffold(
        appBar: AppBar(
          title: Text('ClickyClicker'),
        ),
        body: Center(
          child: Text(wordpair.asPascalCase),
        ),
      ),
    );
  }
}


class RandomWordsState extends State<RandomWords> {

}

class RanodmWords extends StatefulWidget {
  @override
  RandomWordsState.createState() => new RandomWordsState();
}


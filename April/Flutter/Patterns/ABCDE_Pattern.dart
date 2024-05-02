import 'dart:io';

void main() {
    int count = 0;
  for (int i = 0; i < 5; i++) {
    for(int j = 0 ; j <= i  ; j++){
    stdout.write(String.fromCharCode(65+count));
    count++;
  }
  print('');
}
}

import 'dart:io';

void main() {
   int n = 7;
  for (int i = 0; i < n; i++) {
      for(int j=n; j>i; j--){
          stdout.write('  ');
      }
      for(int k=0; k <= i; k++){
          stdout.write('* ');
      }
      for(int l=0; l< i; l++){
          stdout.write('* ');
      }
    print('');
  }
  

}

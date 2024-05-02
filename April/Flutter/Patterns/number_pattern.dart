import 'dart:io';

void main() {
   int n = 7;
  for (int i = 0; i < n; i++) {
      for(int j=n; j>i; j--){
          stdout.write('  ');
      }
      for(int k=i; k >= 0; k--){
          stdout.write((k+1));
          stdout.write(' ');
      }
      for(int l=0; l < i; l++){
          stdout.write((l+2));
          stdout.write(' ');
      }
    print('');
  }
  

}

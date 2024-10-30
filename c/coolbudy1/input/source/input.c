#include <stdio.h>

int inputStringPrompt(char* buffer, const char* prompt, const char* afterPrompt) {
  printf("%s", prompt);
  scanf("%s", buffer);
  printf("%s", afterPrompt);

  return 1;
}
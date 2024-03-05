import subprocess
import platform

if platform.system() == "Windows":
    shell_script_command = "dir"
else:
    shell_script_command = "ls -l"

process = subprocess.Popen(shell_script_command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
stdout, stderr = process.communicate()

if process.returncode == 0:
    print("Shell script command executed successfully:")
    print(stdout.decode())
else:
    print("Error executing shell script command:")
    print(stderr.decode())

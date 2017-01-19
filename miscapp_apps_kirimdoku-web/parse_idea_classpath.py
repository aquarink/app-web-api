import re

f = open("/Users/xigit/Projects/blitzkrieg/.idea_modules/blitzkrieg-build.iml")
result = []
regex = r'.*jar://\$USER_HOME\$(.*)\!.*'
for line in f:
    print line
    test = re.match(regex, line)
    if test:
        result.append("/Users/xigit"+test.group(1))

print ':'.join(result)


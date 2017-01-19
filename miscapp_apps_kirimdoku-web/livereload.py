#!/usr/bin/env python
from livereload.task import Task

files = [
    '*.css',
    '*.less',
    '*.coffee',
    '*.js',
    '*.java',
    '*.scala.html',
    'conf/application.conf',
    'conf/routes',
    'project/Build.scala',
    'project/plugins.sbt',
    'project/build.properties',
]

for file in files:
    Task.add(file)

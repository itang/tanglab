# Rails On JRuby

参考：

1. <http://deployingjruby.blogspot.com/2013/05/how-to-run-rails-400rc1-on-jruby.html>
2. <https://github.com/jruby/jruby-rack/wiki/Rails-Step-by-Step>

## 安装
$ rvm install jruby

$ rvm use jruby

$ jruby -S gem install rails

## 生成项目
$ jruby -S rails new rouge --template http://jruby.org

edit Gemfile

    gem 'activerecord-jdbch2-adapter', :platform => :jruby

$ bundle update


## 配置项目

edit config/database/yml

    # SQLite version 3.x
    # gem 'activerecord-jdbcsqlite3-adapter'
    #
    # Configure Using Gemfile
    # gem 'activerecord-jdbcsqlite3-adapter'
    #
    development:
    adapter: h2
    database: db/development
    username: sa
    password:

    # Warning: The database defined as "test" will be erased and
    # re-generated from your development database when you run "rake".
    # Do not set this db to the same as development or production.
    test:
    adapter: h2
    database: db/test
    username: sa
    password:

    production:
    adapter: h2
    database: db/production
    username: sa
    password:

## 问题

### 解决问题：Illegal key size: possibly you need to install Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files for your JRE

down from ： http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html

$ unzip UnlimitedJCEPolicyJDK7.zip 

$ cp UnlimitedJCEPolicy/*.jar $JAVA_HOME/jre/lib/security/

restart rails server

## 开始开发

### 使用骨架

$ jruby bin/rails generate scaffold comment name:string body:text

$ jruby -S rake db:migrate

$  jruby bin/rails server

访问：http://localhost:3000/comments

## 部署
$ jruby -S gem install warbler

$ jruby -S warble config 

$ RAILS_ENV=production jruby -S rake db:migrate
 

edit  config/warble.rb

    # Additional files/directories to include, above those in config.dirs
    # config.includes = FileList["db"]
    config.includes = FileList["db/production_h2*"]

$ jruby -S warble war

### puam

$ gem install puma

#### m1
puma config.cu

or

RACK_ENV=production bundle exec puma -p 3000

#### m2
edit Gemfile
gem 'puma'

bundle update

$ bin/rails s puma 
or
$ rackup -s Puma


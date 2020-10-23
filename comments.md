### 完成度：
* 整体功能完成度还可以。
* 某些边界情况没有考虑完善。

__Details:__

- \- 无法查询 user！这行在提交时间之后做了修改，但是麻痹大意没有进行回归测试。你以为只改了一行就一定不会出问题，是吧。No，这种时候往往都会出问题。
- \- user 不存在时，查询其 educations，应该返回 404，而不是空数组。
- \- user 不存在时，不能为其创建 educations，但现在可以创建。
- \- 创建 user 时，如果 name 字段没提供、age 不合法等校验失败时，返回了 404，应该是 400。
- \- 创建 education 时，如果字段校验失败，应该返回 400，而不是 404。

### 测试：
* 没写测试。

__Details:__



### 知识点：
* 对 Optional API 的掌握还不够熟练。
* Spring MVC 的一些 annotations 掌握不够熟练。

__Details:__

- \- 下面这几行代码，能看出来对 Optional API 的掌握是不熟练的。
- \- 这个 @RequestMapping 放到这的作用是什么？难道是忘记删除了？
- \- 如果 path 部分有重复，可以提取到 class level 的 @RequestMapping 中去

### 工程实践：
* clean code 方面欠缺明显。

__Details:__
+ \+ 用了 Request DTO，不错
- \- 此处命名不太好。UserDataBaseMock不是什么 mock，就是真实的数据存储，只不过是内存版的。另外，Database 的 b 不需要大写。
- \- 不要搞的都是 static 字段，少用。虽然是内存版的数据存储，但不代表都是 static 就行。
- \- 建议用 equals() 做判等
- \- 不要根据当前 users 的数量来生成id，以后有了删除功能后，会生产重复的 id 的
- \- “id+1”？生成的时候就确定好，不要在每次使用的时候去 +1
- \- educationService 可以 final 的
- \- 通常用 Long，而不是 primitive type 的 long
- \- 这函数名是 copy 过来之后忘记修改了吗？
- \- 很多提交的 log 都不够表意。
- \- 很多提交的提交内容不够内聚，不是迭代开发的。

### 综合：
* 功能方面完成度还可以，但是知识点运用和工程实践方面欠缺较多。

__Details:__




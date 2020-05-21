TwitterSample
=====

### 주요 라이브러리
- **kotlin coroutine**
- **android lifecycle** 
- **android paging**
- **retrofit**
- **moshi**
- **glide**
- **dagger**
- **twitter sdk** - 로그인 시 Oauth만 이용하는 용도

## 테스트
**twitter.properties** 다운로드 후 **root project**로 이동
```xml
CONSUMER_KEY="key"
CONSUMER_SECRET="secret"
```

## 알아둘 사항
1. **twitter sdk**를 로그인 용도로 사용하여서 기기에 트위터 앱이 설치되어있어야합니다.

1. API가 15분에 15번으로 한정되어있습니다.`https://api.twitter.com/1.1/statuses/home_timeline.json`
https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-home_timeline

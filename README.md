# Spring AI PDF RAG Chatbot

## 프로젝트 소개

Spring AI와 OpenAI API, Qdrant Vector Database를 활용하여 PDF 문서 기반 질의응답(RAG, Retrieval-Augmented Generation) 기능을 제공하는 웹 애플리케이션입니다.

사용자는 PDF 문서를 업로드할 수 있으며, 업로드된 문서는 Chunk 단위로 분할된 후 OpenAI Embedding API를 통해 벡터 데이터로 변환되어 Qdrant Vector Database에 저장됩니다.

이후 사용자가 자연어 질문을 입력하면 Similarity Search를 통해 관련 문서를 검색하고, 검색 결과를 기반으로 OpenAI LLM이 답변을 생성합니다.

본 프로젝트를 통해 Spring AI 기반 RAG 서비스 구축, Vector Database 활용, Embedding 기반 문서 검색, LLM 연동 경험을 확보하였습니다.

---

## 기술 스택

### Backend

* Java 21
* Spring Boot 3
* Spring AI
* Spring Security
* Spring Data JPA

### AI

* OpenAI API
* Embedding Model
* RAG (Retrieval-Augmented Generation)

### Vector Database

* Qdrant

### Database

* H2 Database

### Deployment

* Docker
* Docker Compose

### Frontend

* Thymeleaf
* Bootstrap

---

## 시스템 아키텍처

```text
PDF Upload
   ↓
PDF Chunking
   ↓
OpenAI Embedding
   ↓
Qdrant Vector Store
   ↓
User Question
   ↓
Similarity Search
   ↓
Retrieved Context
   ↓
OpenAI LLM
   ↓
Generated Answer
```

---

## 주요 기능

### PDF 업로드

* PDF 문서 업로드
* PagePdfDocumentReader를 활용한 문서 파싱
* 문서 Chunk 분할

### 벡터 저장

* OpenAI Embedding 생성
* Qdrant Vector Database 저장
* 문서 벡터화 및 인덱싱

### AI 질의응답

* 사용자 질문 입력
* Similarity Search 수행
* 검색 결과 기반 답변 생성
* 문서 기반 자연어 질의응답 구현

### Docker 기반 개발 환경

* Docker Compose를 활용한 Qdrant 실행
* Vector Database 로컬 개발 환경 구축

---

## 실행 화면

### PDF 문서 업로드

사용자는 PDF 문서를 업로드하여 벡터 데이터로 변환할 수 있습니다.

![PDF Upload](docs/images/pdf-upload.png)

---

### 사용자 질문 입력

업로드된 문서에 대해 자연어 질문을 입력할 수 있습니다.

![Question Input](docs/images/question-input.png)

---

### AI 기반 문서 질의응답

사용자 질문에 대해 Vector Search와 OpenAI LLM을 활용하여 문서 기반 답변을 생성합니다.

![AI Answer](docs/images/ai-answer.png)

---

### Qdrant Vector Database

PDF 문서 임베딩 데이터가 Qdrant Vector Store에 저장됩니다.

![Qdrant Collection](docs/images/qdrant-collection.png)

---

### Docker 기반 실행 환경

Qdrant Vector Database를 Docker 컨테이너 환경에서 운영합니다.

![Docker Qdrant](docs/images/docker-qdrant.png)

---

## 기술적 도전 및 문제 해결

### Qdrant Collection 생성 문제

#### 문제

PDF 업로드 시 아래와 같은 오류가 발생하였습니다.

```text
Collection `pdf-docs` doesn't exist
```

#### 원인

Qdrant Collection이 생성되지 않은 상태에서 벡터 데이터를 저장하려고 시도하여 발생한 문제였습니다.

#### 해결

```properties
spring.ai.vectorstore.qdrant.initialize-schema=true
```

설정을 추가하여 애플리케이션 시작 시 Collection이 자동 생성되도록 구성하였습니다.

#### 결과

* Collection 자동 생성 확인
* PDF 업로드 성공
* Embedding 데이터 정상 저장
* Similarity Search 정상 동작

---

### Qdrant gRPC 연결 문제

#### 문제

Qdrant 연동 과정에서 gRPC 연결 오류가 발생하였습니다.

```text
First received frame was not SETTINGS
```

#### 원인

Qdrant gRPC 포트(6334)가 열려있지 않은 상태에서 연결을 시도하였습니다.

#### 해결

Docker Compose 설정에 gRPC 포트 매핑을 추가하였습니다.

```yaml
ports:
  - "6333:6333"
  - "6334:6334"
```

#### 결과

Spring AI와 Qdrant 간 gRPC 통신이 정상적으로 수행되었습니다.

---

## 학습 내용

본 프로젝트를 통해 다음 기술을 학습하였습니다.

* Spring AI 활용
* OpenAI API 연동
* RAG(Retrieval-Augmented Generation) 구현
* Vector Database(Qdrant) 활용
* Embedding 기반 Similarity Search 구현
* Docker 기반 AI 서비스 개발 환경 구축
* PDF 문서 기반 AI 검색 서비스 구현

---

## 실행 방법

### Qdrant 실행

```bash
docker compose up -d
```

### 애플리케이션 실행

```bash
./gradlew bootRun
```

### 환경 변수

```text
OPENAI_API_KEY=YOUR_API_KEY
```

---

## 프로젝트 결과

* Spring AI 기반 PDF RAG 챗봇 구현
* OpenAI Embedding을 활용한 문서 벡터화 구현
* Qdrant Vector Database 연동 및 Similarity Search 구현
* PDF 문서 기반 자연어 질의응답 서비스 구현
* Docker 기반 Vector DB 실행 환경 구축
* Spring Boot + AI 서비스 통합 백엔드 개발 경험 확보
* RAG 서비스 개발 및 운영 과정 경험 확보

---

## 향후 개선 계획

* JWT 인증 적용
* 대화 기록 저장 기능
* 다중 PDF 검색 지원
* 사용자별 문서 관리 기능
* AWS EC2 배포
* PostgreSQL 연동
* LangChain 기반 Workflow 구성
* LangGraph 기반 AI Agent 구현
* 사용자 맞춤형 문서 검색 기능 개발

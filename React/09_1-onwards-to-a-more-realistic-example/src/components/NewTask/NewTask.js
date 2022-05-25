import Section from "../UI/Section";
import TaskForm from "./TaskForm";
import useHttp from "../../hooks/use-http";

const NewTask = (props) => {
  const { isLoading, error, sendRequest: sendTaskRequest } = useHttp();

  const createTask = (taskText, taskData) => {
    const generatedId = taskData.name; // firebase-specific => "name" contains generated id
    const createdTask = { id: generatedId, text: taskText };

    props.onAddTask(createdTask);
  };

  const enterTaskHandler = async (taskText) => {
    sendTaskRequest(
      {
        url: "https://react-http-dd4c9-default-rtdb.firebaseio.com/task.json",
        method: "POST",
        headers: {
          "Content-Type" : "appliction/json"
        },
        body: {
          text: taskText,
        },
      },
      // bind메서드는 함수를 사전에 구성할수있도록해준다. 호출 즉시 함수가 실행되지않음
      // 어떤 함수에 대해서도 이를 사전 구성하기위해 사용한다.
      // 첫번째 인자는 실행함수에 this예약어를 사용하는것
      // 두번쨰 인자는 제출된 위 인자를 가져온다는 의미
      createTask.bind(null, taskText)
      );
    };

  return (
    <Section>
      <TaskForm onEnterTask={enterTaskHandler} loading={isLoading} />
      {error && <p>{error}</p>}
    </Section>
  );
};

export default NewTask;

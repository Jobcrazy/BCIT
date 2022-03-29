import React from "react";
import { Table, message, Avatar, Image } from "antd";
import axios from "axios";
import "../../store";
import store from "../../store";

class Goal extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      dataSource: [],
    };

    this.columns = [
      {
        title: "Id",
        dataIndex: "id",
        key: "id",
      },
      {
        title: "Last Name",
        dataIndex: "lastName",
        key: "lastName",
      },
      {
        title: "First Name",
        dataIndex: "firstName",
        key: "firstName",
      },
      {
        title: "Occupation",
        dataIndex: "occupation",
        key: "occupation",
      },
      {
        title: "Gender",
        dataIndex: "gender",
        key: "gender",
      },
      {
        title: "Votes",
        dataIndex: "votes",
        key: "votes",
      },
      {
        title: "Avatar",
        dataIndex: "pictureUrl",
        key: "pictureUrl",
        render: (text, record)=>{
          return <Avatar src={<Image src={record.pictureUrl} style={{ width: 32 }} />} />
        }
      },
    ];

    this.loadData = this.loadData.bind(this);
  }

  async loadData() {
    this.setLoading(true);

    try {
      let result = await axios({
        method: "GET",
        url: "api/toons",
      });

      this.setLoading(false);

      console.log(result.data);

      this.setState({
        dataSource: result.data,
      });
    } catch (err) {
      this.setLoading(false);
      console.log(err);
      message.error("Something went error.");
    }
  }

  componentDidMount() {
    window.document.title = "React Demo";

    this.loadData();
  }

  setLoading(bLoading) {
    let action = {
      type: "setLoading",
      value: bLoading,
    };

    store.dispatch(action);
  }

  render() {
    return (
      <Table
        dataSource={this.state.dataSource}
        bordered
        columns={this.columns}
        rowKey={(record) => record.id}
      />
    );
  }
}

export default Goal;

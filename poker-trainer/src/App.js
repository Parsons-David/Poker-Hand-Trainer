import React, { Component } from 'react';
import './App.css';
function importAll(r) {
  let images = {};
  // eslint-disable-next-line
  r.keys().map((item, index) => { images[item.replace('./', '')] = r(item); });
  return images;
}

const img = importAll(require.context('./img', false, /\.(png|jpe?g|svg|gif)$/));

class App extends Component {
  render() {
    return (
      <div className="App">
        <h1>Poker Trainer</h1>
        <Table/>
      </div>
    );
  }
}

class Card extends Component{
  render() {
    return(
      <div className="Card">
        <img src={this.props.cardSrc} alt="Card"/>
      </div>
    );
  }
}

class Hand extends Component {
  constructor(props){
    super(props);
    this.state = {
      cards: [
        img['b.gif'],
        img['b.gif'],
        img['b.gif'],
        img['b.gif'],
        img['b.gif']
      ]
    };
  }
  toggleStateTest(){
    const state_hand = this.state.cards.slice();
    var updateIndex = state_hand.indexOf(img['b.gif']);
    var new_hand = this.state.cards.slice();
    new_hand[updateIndex] = img['2c.gif'];
    this.setState({
      cards:new_hand
    });
  }
  resetState(){
    var new_hand = this.state.cards.slice();
    for(var i in new_hand){
      new_hand[i] = img['b.gif'];
    }
    this.setState({
      cards:new_hand
    });
  }
  renderCard(i){
    return(
      <Card
      cardSrc={this.state.cards[i]}
      />
    );
  }
  render() {
    var rendered_hand = [];
    for(var i = 0; i < this.state.cards.length; i++){
      rendered_hand.push(this.renderCard(i));
    }
    return (
      <div>
        {rendered_hand}
        <br/>
        <br/>
        <button onClick={() => this.toggleStateTest()}>Test</button>
        <br/>
        <button onClick={() => this.resetState()}>Reset</button>
      </div>
    );
  }
}

class Table extends Component {
  render() {
    return (
      <div className="Table">
        <Hand/>
      </div>
    );
  }
}

export default App;

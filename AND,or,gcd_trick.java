 static class Pair{
        int first;
        int second;
        Pair(int first,int second){
            this.first=first;
            this.second=second;
            }
    }
    HashMap<Integer,Long> and_trick(int arr[],int n){
        HashMap<Integer,Long> and_values = new HashMap<>();
        // here trick starts   form a particular index value ai there are log(ai) different and values
        HashMap<Integer,Integer> prev_and  = new HashMap<>();
        for (int i = n-1; i >=0 ; i--) {
          HashMap<Integer,Integer> next_and  = new HashMap<>();
          for(int aa : prev_and.keySet()){
             if(next_and.containsKey(aa&arr[i])){
              next_and.put(aa&arr[i],Math.max(next_and.get(aa&arr[i]),prev_and.get(aa)));
             }
             else{
              next_and.put(aa&arr[i],prev_and.get(aa));
             }
          }
          if(next_and.containsKey(arr[i])){
              next_and.put(arr[i],Math.max(next_and.get(arr[i]),i));
             }
             else{
              next_and.put(arr[i],i);
          }
          //hm has (k,v) as (x,y) x tells us that the &value x till index v satrting at index i; 
          prev_and = next_and;
         ArrayList<Pair> ranges = new ArrayList<>();
          for(int and_val : next_and.keySet()){
              ranges.add(new Pair(and_val,next_and.get(and_val)));
          }
         Collections.sort(ranges,new Comparator<Pair>() {
               @Override
               public int compare(Pair o1, Pair o2) {
                  return o1.second-o2.second;
                  }
          });
        // sorting on the basis of starting index
      //   System.out.println("Starting at. "+" "+i);
          for (int j = 0; j < ranges.size(); j++) {
              if(j==0){
                  // System.out.println(i+" "+ranges.get(j).second+" "+ranges.get(j).first);
                //[i.....ranges[j].second]; -> val-> ranges.first
                    if(and_values.containsKey(ranges.get(j).first)){

                      and_values.put(ranges.get(j).first,and_values.get(ranges.get(j).first)+(ranges.get(j).second-i+1));
                    }
                    else{
                      and_values.put(ranges.get(j).first,(long)(ranges.get(j).second-i+1));
                    }
              }
              else{
                  // System.out.println((ranges.get(j-1).second+1)+" "+ranges.get(j).second+" "+ranges.get(j).first);
                  //[ranges[j-1].second+1 ..... ranges.second]; -> val-> ranges[j].first
                  if(and_values.containsKey(ranges.get(j).first)){
                      and_values.put(ranges.get(j).first,and_values.get(ranges.get(j).first)+(ranges.get(j).second-(ranges.get(j-1).second)));
                    }
                    else{
                      and_values.put(ranges.get(j).first,(long)(ranges.get(j).second-(ranges.get(j-1).second)));
                    }
              }
          }
          //now we have all and values in map and_values with there frequencies of qccurences
        }
      //now ab (and / gcd / or ) >k ==k <k jo find karna hai karo
         return and_values;
    }
